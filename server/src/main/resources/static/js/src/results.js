async function goHome() {
  await fetch('http://localhost:8080/results', {method: "POST"});
};