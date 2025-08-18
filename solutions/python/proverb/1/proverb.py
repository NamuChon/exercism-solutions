def proverb(*input_data: str, qualifier: str) -> list[str]:
    if not input_data: return []
    return ([f"For want of a {input_data[i-1]} the {input_data[i]} was lost." for i in range(1, len(input_data))]
            + ["And all for the want of a {}{}.".format(qualifier + " " if qualifier else "", input_data[0])])
