from sys import exit

def check_user_input(user_input, valid_input = None):
    """"""
    if user_input == 'q()':
        exit()
    if valid_input != None:
        if user_input not in valid_input:
            return_code = 1
        else:
            return_code = 0
    else:
        return_code = 0
    return user_input, return_code