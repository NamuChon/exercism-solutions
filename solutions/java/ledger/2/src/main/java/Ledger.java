import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Ledger {
    public LedgerEntry createLedgerEntry(String d, String desc, int c) {
        LedgerEntry le = new LedgerEntry();
        le.setChange(c);
        le.setDescription(desc);
        le.setLocalDate(LocalDate.parse(d));
        return le;
    }

    public String format(String cur, String loc, LedgerEntry[] entries) {
        String header, curSymb, datPat, decSep, thSep, amountSep;
        if (cur.equals("USD")) {
            curSymb = "$";
        } else if (cur.equals("EUR")) {
            curSymb = "€";
        } else {
            throw new IllegalArgumentException("Invalid currency");
        }
        if (loc.equals("en-US")) {
            datPat = "MM/dd/yyyy";
            decSep = ".";
            thSep = ",";
            amountSep = "";
            header = "Date       | Description               | Change       ";
        } else if (loc.equals("nl-NL")) {
            datPat = "dd/MM/yyyy";
            decSep = ",";
            thSep = ".";
            amountSep = " ";
            header = "Datum      | Omschrijving              | Verandering  ";
        } else {
            throw new IllegalArgumentException("Invalid locale");
        }

        StringBuilder s = new StringBuilder(header);

        if (entries.length > 0) {
            List<LedgerEntry> neg = new ArrayList<>();
            List<LedgerEntry> pos = new ArrayList<>();
            for (int i = 0; i < entries.length; i++) {
                LedgerEntry e = entries[i];
                if (e.getChange() >= 0) {
                    pos.add(e);
                } else {
                    neg.add(e);
                }
            }

            neg.sort((o1, o2) -> o1.getLocalDate().compareTo(o2.getLocalDate()));
            pos.sort((o1, o2) -> o1.getLocalDate().compareTo(o2.getLocalDate()));

            List<LedgerEntry> all = new ArrayList<>();
            all.addAll(neg);
            all.addAll(pos);

            for (LedgerEntry e : all) {
                String date = e.getLocalDate().format(DateTimeFormatter.ofPattern(datPat));

                String desc = e.getDescription();
                if (desc.length() > 25) {
                    desc = desc.substring(0, 22) + "...";
                }
                
                int val = (int) Math.abs(e.getChange());
                String dec = String.format("%02d", val % 100);
                String amount = String.format("%,d", val / 100).replace(",", thSep);
                
                amount = curSymb + amountSep + amount + decSep + dec;
                amount = e.getChange() < 0 ? (loc.equals("en-US") ? "(" + amount + ")" : curSymb + " -" + amount.replace(curSymb, "").trim() + " ") : amountSep + amount + " ";
                
                s.append("\n" + String.format("%s | %-25s | %13s", date, desc, amount));
            }
        }
        return s.toString();
    }

    public static class LedgerEntry {
        private LocalDate localDate;
        private String description;
        private double change;

        public LocalDate getLocalDate() {
            return localDate;
        }

        public void setLocalDate(LocalDate localDate) {
            this.localDate = localDate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getChange() {
            return change;
        }

        public void setChange(double change) {
            this.change = change;
        }
    }

}
