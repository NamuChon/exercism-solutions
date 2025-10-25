class Record:
    def __init__(self, id: int, parent_id: int):
        self.id = id
        self.parent_id = parent_id


class Node:
    def __init__(self, node_id: int):
        self.node_id = node_id
        self.children = []


def BuildTree(records: list[Record]) -> Node:
    if not records: return None

    records.sort(key = lambda r: r.id)
    ids = [r.id for r in records]
    if ids != list(range(len(ids))): raise ValueError("Record id is invalid or out of order.")
    if records[0].parent_id != 0: raise ValueError("Node parent_id should be smaller than its record_id.")
    nodes = {r.id: Node(r.id) for r in records}
    for record in records[1:]:
        if record.id == record.parent_id: raise ValueError("Only root should have equal record and parent id.")
        if record.id < record.parent_id: raise ValueError("Node parent_id should be smaller than its record_id.")
        nodes[record.parent_id].children.append(nodes[record.id])

    return nodes[0]
