def grade_answer(answer, target_sample, form, options, english, total):
    """"""
    # retrieve the english translation of the answer
    answer_english = options.loc[options[form] == answer, 'English'].iloc[0]
    # determine if the correct answer was choosen
    result = 1 if answer_english == english else 0
    # update running total of game
    total = total + result
    # generate feedback response
    response = 'Correct' if result == 1 else f"Incorrect, the correct answer was {target_sample['Nominative Singular'].iloc[0]}"
    print(response)
    return total