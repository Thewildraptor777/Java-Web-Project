const playButtonDiv = document.getElementById("play-button-div");
const playButton = document.getElementById("play-button");
const pauseButtonDiv = document.getElementById("pause-button-div");
const pauseButton = document.getElementById("pause-button");
const shuffleButton = document.getElementById("shuffle-button");
const shuffleIcon = document.getElementById("shuffle-icon");
const loopButton = document.getElementById("loop-button");
const loopIcon = document.getElementById("loop-icon");

playButton.addEventListener("click", () => {
    pauseButtonDiv.classList.remove("clear");
    playButtonDiv.classList.add("clear");
});

pauseButton.addEventListener("click", () => {
    playButtonDiv.classList.remove("clear");
    pauseButtonDiv.classList.add("clear");
    pauseButtonDiv.classList.add("button-div");
});


shuffleButton.addEventListener("click", () => {
    if (shuffleIcon.style.color != "red") {
        shuffleIcon.style.color = "red";
        shuffle = true;
        out = false;
    } else {
        shuffleIcon.style.color = "black";
        shuffle = false;
    }
});


loopButton.addEventListener("click", () => {
    if (loopIcon.style.color != "red") {
        loopIcon.style.color = "red";
        loop = true;
        out = false;
    } else {
        loopIcon.style.color = "black";
        loop = false;
    }
});
