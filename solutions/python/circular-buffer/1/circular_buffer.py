class BufferFullException(BufferError):
    """Exception raised when CircularBuffer is full.

    message: explanation of the error.

    """
    def __init__(self, message):
        self.message = message


class BufferEmptyException(BufferError):
    """Exception raised when CircularBuffer is empty.

    message: explanation of the error.

    """
    def __init__(self, message):
        self.message = message


class CircularBuffer:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.elements = [None] * capacity
        self.read_pos = self.write_pos = 0

    def read(self):
        if not (element := self.elements[self.read_pos]):
            raise BufferEmptyException("Circular buffer is empty")
        self.elements[self.read_pos] = None
        self.read_pos = self.shift(self.read_pos)
        return element
        

    def write(self, data):
        if self.elements[self.write_pos]:
            raise BufferFullException("Circular buffer is full")
        self.elements[self.write_pos] = data
        self.write_pos = self.shift(self.write_pos)

    def overwrite(self, data):
        if self.elements[self.write_pos]:
            self.elements[self.write_pos] = data
            self.write_pos = self.shift(self.write_pos)
            self.read_pos = self.shift(self.read_pos)
        else:
            self.write(data)

    def clear(self):
        self.elements = [None] * self.capacity

    def shift(self, position):
        return (position + 1) % self.capacity
