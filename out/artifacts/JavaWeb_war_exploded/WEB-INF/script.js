/* script.js */
const heartbeat = document.querySelector('.heartbeat');

let intervalId;

function startHeartbeat() {
  intervalId = setInterval(() => {
    heartbeat.classList.add('heartbeat');
  }, 1000);
}

function stopHeartbeat() {
  clearInterval(intervalId);
  heartbeat.classList.remove('heartbeat');
}