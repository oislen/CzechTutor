async function submitForm() {
    // extract page elements
    let fromLanguage = document.querySelector('input[name="fromLanguage"]:checked').value;
    let toLanguage = document.querySelector('input[name="toLanguage"]:checked').value;
    // set fetch arguments
    let payload = JSON.stringify({"fromLanguage":fromLanguage, "toLanguage":toLanguage});
    let headers = {'Accept':'application/json', "Content-Type":"application/json"};
    await fetch('http://localhost:8080/home', {method: "POST", body: payload, headers:headers}).then(result => result.text()).then(text => alert(text));
};