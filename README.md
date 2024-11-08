# Czech Tutor

A simple single page website for practising Czech phrases and vocabulary.

# Data Source

Czech to English phrases and vocabulary sourced from:

- https://www.manythings.org/anki/
- https://apps.ankiweb.net/
- https://tatoeba.org/en

# To Do:

1. Fix Czech java readfile encoding for ces.txt
   1. Add basic string cleaning when determining answers 
      1. e.g. strip, decode, lowercase, whitespace, punctuation
2. Create test maven cases for all java modules
3. Create github actions workflow for running maven tests when pushing to dev and pushing / merging to main
