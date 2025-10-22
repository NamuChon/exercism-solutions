class EmptyListException(Exception):
    def __init__(self, message: str):
        self.message = message


class Node:
    def __init__(self, value, next=None):
        self.__value = value
        self.__next = next

    def value(self):
        return self.__value

    def next(self):
        return self.__next


class LinkedList:
    def __init__(self, values=[]):
        self.__head = None
        self.__len = 0
        for value in values:
            self.push(value)

    def __iter__(self):
        cur = self.__head
        while cur:
            yield cur.value()
            cur = cur.next()

    def __len__(self):
        return self.__len

    def head(self):
        self.raise_if_empty()
        return self.__head

    def push(self, value):
        self.__head = Node(value, self.__head)
        self.__len += 1

    def pop(self):
        self.raise_if_empty()
        head_value = self.__head.value()
        self.__head = self.__head.next()
        self.__len -= 1
        return head_value

    def reversed(self):
        return reversed(list(iter(self)))

    def raise_if_empty(self):
        if self.__len == 0: raise EmptyListException("The list is empty.")
