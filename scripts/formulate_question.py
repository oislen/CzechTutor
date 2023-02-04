def formulate_question(english, form, options):
    """"""
    if form == 'Nominative Singular':
        question = f'What is Czech for {english.title()}:'
    else:
        question = f'What is Czech for {english.title()}(s):'
    # generate question options
    valid_options = options[form].to_list()
    question_options = ''.join([f'{i}. {x} \n' for i, x in enumerate(valid_options)])
    print(question)
    print(question_options)
    return valid_options
