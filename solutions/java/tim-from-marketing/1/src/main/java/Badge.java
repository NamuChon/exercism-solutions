class Badge {
    public String print(Integer id, String name, String department) {
        StringBuilder sb = new StringBuilder();
        if (id != null) sb.append("[" + id + "]" + " - ");
        sb.append(name + " - ");
        sb.append((department == null) ? "OWNER" : department.toUpperCase());
        return sb.toString();
    }
}
