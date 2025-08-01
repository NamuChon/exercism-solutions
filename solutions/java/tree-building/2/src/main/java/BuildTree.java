import java.util.*;
class BuildTree {

    TreeNode buildTree(ArrayList<Record> records) throws InvalidRecordsException {
        int size = records.size();
        if (size == 0) return null;
        records.sort(Comparator.comparing(Record::getRecordId));
        for (int i = 0; i < size; i++) {
            Record record = records.get(i);
            int recordId = record.getRecordId();
            int parentId = record.getParentId();
            if (recordId != i || parentId > recordId || recordId == 0 && parentId != 0) throw new InvalidRecordsException("Invalid Records");
        }
        
        Queue<TreeNode> queue = new ArrayDeque<>();
        Set<Integer> used = new HashSet<>();
        TreeNode root = new TreeNode(0);
        records.remove(0);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int nodeId = node.getNodeId();
            used.add(nodeId);
            List<TreeNode> children = records.stream()
                .filter(r -> r.getParentId() == nodeId)
                .map(r -> new TreeNode(r.getRecordId()))
                .toList();
            
            ArrayList<TreeNode> nodeChildren = node.getChildren();
            for (TreeNode child : children) {
                int childId = child.getNodeId();
                if (used.contains(childId)) throw new InvalidRecordsException("Invalid Records");
                queue.add(child);
                nodeChildren.add(child);
            }
        }
        if (used.size() != size) throw new InvalidRecordsException("Invalid Records");
        return root;
    }

}
