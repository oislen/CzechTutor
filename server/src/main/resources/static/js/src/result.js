function submitForm() {
  // set fetch arguments
  let lessonId = window.location.href.split('/').slice(-1)[0];
  let payload = JSON.stringify({});
  let headers = {'Accept':'application/json', "Content-Type":"application/json"};
  let url = 'http://localhost:8080/result/'.concat(lessonId);
  fetch(url, {method: "POST", body: payload, headers:headers, redirect: 'follow'});
};