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
        self.elements = []
        self.capacity = capacity

    def read(self):
        if not self.elements:
            raise BufferEmptyException("Circular buffer is empty")
        return self.elements.pop(0)

    def write(self, data):
        if len(self.elements) >= self.capacity:
            raise BufferFullException("Circular buffer is full")
        self.elements.append(data)

    def overwrite(self, data):
        if len(self.elements) >= self.capacity:
            self.elements.pop(0)
        self.elements.append(data)

    def clear(self):
        self.elements = []