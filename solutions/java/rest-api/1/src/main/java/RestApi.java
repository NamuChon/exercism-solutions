import org.json.*;
import java.util.*;
import java.util.stream.*;
class RestApi {
    private final Map<String, User> users;
    RestApi(User... users) {
        this.users = Arrays.stream(users).collect(Collectors.toMap(u -> u.name(), u -> u, (k1, k2) -> k1, HashMap::new));
    }

    String get(String url) {
        return getUserListJSON(new JSONArray(users.keySet().stream().map(this::getUserJSON).toList())).toString();
    }

    String get(String url, JSONObject payload) {
        JSONArray userArray = new JSONArray();
        for (Object name : payload.getJSONArray("users")) {
            userArray.put(getUserJSON(name.toString()));
        }
        return getUserListJSON(userArray).toString();
    }

    String post(String url, JSONObject payload) {
        if (url.equals("/add")) {
            String name = payload.getString("user");
            users.put(name, User.builder().setName(name).build());
            return getUserJSON(name).toString();
        } else if (url.equals("/iou")) {
            String lenderName = payload.getString("lender");
            String borrowerName = payload.getString("borrower");
            double amount = payload.getDouble("amount");
            updateIou(lenderName, borrowerName, amount);
            JSONArray resultArray = lenderName.compareTo(borrowerName) < 0 ? new JSONArray().put(lenderName).put(borrowerName) : new JSONArray().put(borrowerName).put(lenderName);
            return get("/users", getUserListJSON(resultArray));
        }
        return null;
    }

    private JSONObject getUserListJSON(JSONArray array) {
        return new JSONObject().put("users", array);
    }

    private JSONObject getUserJSON(String name) {
        User user = users.get(name);
        JSONObject owes = new JSONObject();
        JSONObject owedBy = new JSONObject();
        double balance = 0;
        for (Iou owe : user.owes()) {
            owes.put(owe.name, owe.amount);
            balance -= owe.amount;
        }
        for (Iou owed : user.owedBy()) {
            owedBy.put(owed.name, owed.amount);
            balance += owed.amount;
        }
        return new JSONObject().put("name", name).put("owes", owes).put("owedBy", owedBy).put("balance", balance);
        
    }

    private void updateIou(String lenderName, String borrowerName, double amount) {
        User lender = users.get(lenderName);
        User borrower = users.get(borrowerName);
        List<Iou> lenderOwes = new ArrayList<>(lender.owes());
        List<Iou> lenderOwedBy = new ArrayList<>(lender.owedBy());
        List<Iou> borrowerOwes = new ArrayList<>(borrower.owes());
        List<Iou> borrowerOwedBy = new ArrayList<>(borrower.owedBy());
        lenderOwedBy.add(new Iou(borrowerName, amount));
        borrowerOwes.add(new Iou(lenderName, amount));
        updateUser(lenderName, lenderOwes, lenderOwedBy);
        updateUser(borrowerName, borrowerOwes, borrowerOwedBy);
    }

    private void updateUser(String name, List<Iou> owes, List<Iou> owedBy) {
        User.Builder newBuild = User.builder().setName(name);
        for (Iou owe : owes) {
            double oweAmount = owe.amount;
            double alsoOwedBy = samePersonAmount(owe.name, owedBy);
            if (alsoOwedBy != 0.0 && oweAmount <= alsoOwedBy) continue;
            newBuild.owes(owe.name, oweAmount - alsoOwedBy);
        }
        for (Iou owed : owedBy) {
            double owedAmount = owed.amount;
            double alsoOwes = samePersonAmount(owed.name, owes);
            if (alsoOwes != 0.0 && owedAmount <= alsoOwes) continue;
            newBuild.owedBy(owed.name, owedAmount - alsoOwes);
        }
        users.replace(name, newBuild.build());
    }
    private double samePersonAmount(String name, List<Iou> list) {
        return list.stream().filter(iou -> iou.name.equals(name)).mapToDouble(iou -> iou.amount).findFirst().orElse(0.0);
    }
}