const baseUrl = "http://localhost:8081/user";

async function loadUsers() {
  const res = await fetch(baseUrl);
  const users = await res.json();
  const table = document.querySelector("#userTable tbody");
  table.innerHTML = "";
  users.forEach(u => {
    table.innerHTML += `
      <tr>
        <td>${u.id}</td>
        <td>${u.name}</td>
        <td>${u.age}</td>
        <td>${u.gender}</td>
        <td><button class="delete-btn" onclick="deleteUser(${u.id})">🗑️ Delete</button></td>
      </tr>`;
  });
}

async function addUser() {
  const name = document.getElementById("userName").value;
  const age = document.getElementById("userAge").value;
  const gender = document.getElementById("userGender").value;

  if (!name || !age || !gender) return alert("Fill all fields!");

  await fetch(baseUrl, {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({name, age, gender})
  });

  loadUsers();
}

async function deleteUser(id) {
  await fetch(`${baseUrl}/${id}`, {method: "DELETE"});
  loadUsers();
}

loadUsers();