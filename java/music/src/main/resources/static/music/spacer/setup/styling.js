
// Get references to the play and pause buttons
var playButton = document.getElementById('play-button');
var pauseButton = document.getElementById('pause-button');

// Add event listeners to the buttons
playButton.addEventListener('click', function() {
  togglePlayPauseButtons();
});

pauseButton.addEventListener('click', function() {
  togglePlayPauseButtons();
});

// Helper function to toggle the visibility of the play and pause buttons
function togglePlayPauseButtons() {
  playButton.classList.toggle('d-none');
  pauseButton.classList.toggle('d-none');
}

// Get a reference to the shuffle button and its icon
const shuffleButton = document.getElementById("shuffleButton");
const shuffleIcon = document.getElementById("shuffle-icon");

// Add a click event listener to the button
shuffleButton.addEventListener("click", function() {
  // Toggle the "active" class on the icon
  shuffleIcon.classList.toggle("active");
});

// Get a reference to the shuffle button and its icon
const loopButton = document.getElementById("loopButton");
const loopIcon = document.getElementById("loop-icon");

// Add a click event listener to the button
loopButton.addEventListener("click", function() {
  // Toggle the "active" class on the icon
  loopIcon.classList.toggle("active");
});