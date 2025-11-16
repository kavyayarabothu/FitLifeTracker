const workoutBase = "http://localhost:8081/workouts";
const userBase = "http://localhost:8081/user";

async function loadUsers() {
  const res = await fetch(userBase);
  const users = await res.json();
  const userSelect = document.getElementById("userSelect");
  userSelect.innerHTML = users.map(u => `<option value="${u.id}">${u.name}</option>`).join("");
}

async function loadWorkouts() {
  const userId = document.getElementById("userSelect").value;
  const res = await fetch(`${workoutBase}/user/${userId}`);
  const workouts = await res.json();

  const table = document.querySelector("#workoutTable tbody");
  table.innerHTML = workouts.map(w => `
    <tr>
      <td>${w.id}</td>
      <td>${w.user ? `${w.user.name} <span class='userid'>(#${w.user.id})</span>` : '-'}</td>
      <td>${w.workoutType}</td>
      <td>${w.duration} mins</td>
      <td>${w.weight} kg</td>
      <td>${w.caloriesBurned.toFixed(1)}</td>
      <td>${w.date}</td>
      <td><button class="delete-btn" onclick="deleteWorkout(${w.id})">🗑️ Delete</button></td>
    </tr>
  `).join("");
}

async function addWorkout() {
  const userId = document.getElementById("userSelect").value;
  const workoutType = document.getElementById("workoutType").value;
  const duration = document.getElementById("duration").value;
  const weight = document.getElementById("weight").value;

  if (!userId || !workoutType || !duration || !weight) return alert("Fill all fields!");

  await fetch(`${workoutBase}/${userId}`, {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({ workoutType, duration, weight, user: { id: userId } })
  });

  loadWorkouts();
}

async function deleteWorkout(id) {
  await fetch(`${workoutBase}/${id}`, { method: "DELETE" });
  loadWorkouts();
}

document.getElementById("userSelect").addEventListener("change", loadWorkouts);
loadUsers().then(loadWorkouts);