async function submitForm() {
    // extract page elements
    let answer1 = document.querySelector('input[name="question1"]:checked').value;
    let answer2 = document.querySelector('input[name="question2"]:checked').value;
    let answer3 = document.querySelector('input[name="question3"]:checked').value;
    let answer4 = document.querySelector('input[name="question4"]:checked').value;
    let answer5 = document.querySelector('input[name="question5"]:checked').value;
    let answer6 = document.querySelector('input[name="question6"]:checked').value;
    // set fetch arguments
    let payload = JSON.stringify({"answer1":answer1, "answer2":answer2,"answer3":answer3, "answer4":answer4,"answer5":answer5, "answer6":answer6});
    let headers = {'Accept':'application/json', "Content-Type":"application/json"};
    await fetch('http://localhost:8080/lesson', {method: "POST", body: payload, headers:headers}).then(result => result.text()).then(text => alert(text));
};