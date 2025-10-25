class Record:
    def __init__(self, record_id: int, parent_id: int):
        self.record_id = record_id
        self.parent_id = parent_id


class Node:
    def __init__(self, node_id: int):
        self.node_id = node_id
        self.children = []


def BuildTree(records: list[Record]):
    if not records: return None
    root, *rest = sorted(records, key = lambda x: x.record_id)
    ids = [root.record_id] + [r.record_id for r in rest]
    if len(set(ids)) != len(ids) or ids[0] != 0 or ids[-1] != len(ids) - 1: raise ValueError("Record id is invalid or out of order.")
    if root.parent_id: raise ValueError("Node parent_id should be smaller than its record_id.")
    
    nodes = [Node(root.record_id)]
    for record in rest:
        if record.record_id < record.parent_id: raise ValueError("Node parent_id should be smaller than its record_id.")
        if record.record_id == record.parent_id: raise ValueError("Only root should have equal record and parent id.")
        node = Node(record.record_id)
        parent = next(filter(lambda node: node.node_id == record.parent_id, nodes))
        parent.children.append(node)
        nodes.append(node)

    return nodes[0]
