async function submitForm() {
    let fromLanguage = document.querySelector('input[name="fromLanguage"]:checked').value;
    let toLanguage = document.querySelector('input[name="toLanguage"]:checked').value;
    await fetch('http://localhost:8080/home', {method: "POST", body: JSON.stringify({"fromLanguage":fromLanguage, "toLanguage":toLanguage}), headers:{'Accept':'application/json', "Content-Type":"application/json"}}).then(result => result.text()).then(text => alert(text));
};