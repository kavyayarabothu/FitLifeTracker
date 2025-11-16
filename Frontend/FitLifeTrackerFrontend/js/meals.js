const mealBase = "http://localhost:8081/meals";
const userBase = "http://localhost:8081/user";

async function loadUsers() {
  const res = await fetch(userBase);
  const users = await res.json();
  const userSelect = document.getElementById("userSelect");
  userSelect.innerHTML = users.map(u => `<option value="${u.id}">${u.name}</option>`).join("");
}

async function loadMeals() {
  const userId = document.getElementById("userSelect").value;
  const res = await fetch(`${mealBase}/user/${userId}`);
  const meals = await res.json();
  const table = document.querySelector("#mealTable tbody");
  table.innerHTML = meals.map(m => `
    <tr>
      <td>${m.id}</td>
      <td>${m.user ? `${m.user.name} <span class='userid'>(#${m.user.id})</span>` : '-'}</td>
      <td>${m.mealName}</td>
      <td>${m.mealType}</td>
      <td>${m.calories}</td>
      <td>${m.date}</td>
      <td><button class="delete-btn" onclick="deleteMeal(${m.id})">🗑️ Delete</button></td>
    </tr>`).join("");
}

async function addMeal() {
  const mealName = document.getElementById("mealName").value;
  const mealType = document.getElementById("mealType").value;
  const userId = document.getElementById("userSelect").value;

  await fetch(`${mealBase}/${userId}`, {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({
      mealName, mealType,
      user: { id: userId }
    })
  });

  loadMeals();
}

async function deleteMeal(id) {
  await fetch(`${mealBase}/${id}`, {method: "DELETE"});
  loadMeals();
}

document.getElementById("userSelect").addEventListener("change", loadMeals);
loadUsers().then(loadMeals);