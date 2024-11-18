async function submitForm() {
    let formData = new FormData();
    await fetch('http://localhost:8080/home', {method: "POST", body: formData}).then(result => result.text()).then(text => alert(text));
};