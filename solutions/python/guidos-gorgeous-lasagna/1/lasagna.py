"""Functions used in preparing Guido's gorgeous lasagna.

Learn about Guido, the creator of the Python language:
https://en.wikipedia.org/wiki/Guido_van_Rossum

This is a module docstring, used to describe the functionality
of a module and its functions and/or classes.
"""

EXPECTED_BAKE_TIME = 40
PREPARATION_TIME = 2


def bake_time_remaining(elapsed_bake_time):
    """Calculate the bake time remaining.

    :param elapsed_bake_time: int - baking time already elapsed.
    :return: int - remaining bake time (in minutes) derived from 'EXPECTED_BAKE_TIME'.

    Function that takes the actual minutes the lasagna has been in the oven as
    an argument and returns how many minutes the lasagna still needs to bake
    based on the `EXPECTED_BAKE_TIME`.
    """
    
    return EXPECTED_BAKE_TIME - elapsed_bake_time


def preparation_time_in_minutes(number_of_layers):
    """Calculate the preparation time in minutes.

    :param number_of_layers: int - the number of layers added to the lasagna.
    :return: int - preparation time (in minutes) derived from 'PREPARATION_TIME'.

    Function that takes the number of layers added to the lasagna as
    an argument and returns how many minutes has been elapsed in preparation
    based on the `PREPARATION_TIME`.
    """
    
    return PREPARATION_TIME * number_of_layers


def elapsed_time_in_minutes(number_of_layers, elapsed_bake_time):
    """Calculate the elapsed time in minutes.

    :param number_of_layers: int - the number of layers added to the lasagna.
    :param elapsed_bake_time: int - the number of minutes the lasagna has spent baking in the oven already.
    :return: int - elapsed time (in minutes) derived from 'EXPECTED_BAKE_TIME' and 'PREPARATION_TIME'.

    Function that takes the number of layers added to the lasagna and the number of minutes the lasagna has spent baking in the oven as
    an argument and returns how many minutes has been elapsed in total
    based on the 'EXPECTED_BAKE_TIME' and `PREPARATION_TIME`.
    """
    
    return preparation_time_in_minutes(number_of_layers) + elapsed_bake_time