const userBase = "http://localhost:8081/user";
const reportBase = "http://localhost:8081/reports";

async function loadUsers() {
  const res = await fetch(userBase);
  const users = await res.json();
  const userSelect = document.getElementById("userSelect");
  userSelect.innerHTML = users.map(u => `<option value="${u.id}">${u.name}</option>`).join("");
}

async function loadReport() {
  const userId = document.getElementById("userSelect").value;
  if (!userId) return alert("Select a user!");

  const res = await fetch(`${reportBase}/${userId}`);
  const data = await res.text();

  const parts = data.split("|");
  document.getElementById("userName").innerText = parts[0].trim();
  document.getElementById("calIn").innerText = parts[1].trim();
  document.getElementById("calOut").innerText = parts[2].trim();
  document.getElementById("calBalance").innerText = parts[3].trim();

  document.getElementById("reportCard").classList.remove("hidden");
}

loadUsers();
