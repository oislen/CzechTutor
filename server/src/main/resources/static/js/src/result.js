async function submitForm() {
  // set fetch arguments
  let payload = JSON.stringify({});
  let headers = {'Accept':'application/json', "Content-Type":"application/json"};
  await fetch('http://localhost:8080/result', {method: "POST", body: payload, headers:headers}).then(result => result.text()).then(text => alert(text));
};