const tracksList = document.getElementById("playlist-tracks");
const tracks = tracksList.getElementsByTagName("li");
const total = document.getElementById("total-songs");
const playButtonDiv = document.getElementById("play-button-div");
const pauseButtonDiv = document.getElementById("pause-button-div");
const playButton = document.getElementById("play-button");
const pauseButton = document.getElementById("pause-button");
const audio = document.getElementById("audio");
let currentIndex = 0;
let shuffle = false;
let loop = false;
let out = false;

function play() {
    audio.src = links[currentIndex];
    audio.play();
    pauseButtonDiv.classList.remove("clear");
    playButtonDiv.classList.add("clear"); pauseButtonDiv.classList.add("button-div");
} function pause(

) {
    ; playButtonDiv.classList.remove("clear");
    pauseButtonDiv.classList.add("button-div"); pauseButtonDiv.classList.add("clear"); audio.pause()
}
playButton.addEventListener("click", () => {
    pauseButtonDiv.classList.remove("clear");
    playButtonDiv.classList.add("clear");
    play();
    out = false;
});

pauseButton.addEventListener("click", () => {
    playButtonDiv.classList.remove("clear");
    pauseButtonDiv.classList.add("clear");
    pause();
    out = false;
});

const shuffleIcon = document.getElementById("shuffle-icon");
const shuffleButton = document.getElementById("shuffle-button");
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

const loopIcon = document.getElementById("loop-icon");
const loopButton = document.getElementById("loop-button");
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

function length() {
    var duration = audio.duration;
    const minutes = Math.floor(duration / 60);
    const seconds = Math.floor(duration % 60);
    if(seconds.toString().length==1){
        finalSeconds="0"+seconds.toString()
    }else{finalSeconds=seconds.toString()}
    let final = minutes.toString() + "." + finalSeconds;

    document.getElementById("length").innerHTML = "Length: " + final;
}

audio.onloadedmetadata = () => {
    length();
};

function updateInfo() {
    document.getElementById("title").innerHTML = playlistData[currentIndex][3];
    document.getElementById("artist").innerHTML = playlistData[currentIndex][4];
    document.getElementById("music-image").src = playlistData[currentIndex][2];
    document.getElementById("no-more").innerHTML = "";
}

function next() {
    if(currentIndex==0){
        out=false
    }
    if (out != true) {
        if (shuffle == true) {
            let randIndex = Math.floor(Math.random() * playlistData.length);
            currentIndex = randIndex;
            play();
        } else {
            currentIndex++;
            play();
        }

        play();
    }

    if (loop == true) {
        let loopIndex = playlistData.length - currentIndex - 1;
        if (loopIndex == 0) {
            currentIndex = -1;
        }
    } else if (currentIndex == playlistData.length) {
        document.getElementById("no-more").innerHTML = "No more songs";
        out = true;
    } else {
        out = false;
    }

    if (out != true) {
        updateInfo();
    }
}

function prev() {
    if (currentIndex == 0) {
        out = true;
        pause();
      
    }
    if (currentIndex == playlistData.length) {
        out = false;
    }
    if (out == true) {
        document.getElementById("no-more").innerHTML = "No more";
       pause()
        console.log(currentIndex)
    } else {
      
        play();
        if (currentIndex == 0) {
            currentIndex = playlistData.length;
        }
        currentIndex -= 1;
        play();
        updateInfo();
        out = false;
    }
}

audio.onended = () => {
    next();
};

document.onload = () => {
  
     audio.src=links[currentIndex]

  updateInfo();
    length()
   document.getElementById("total-songs").innerHTML = "Total number of songs:" + playlistData.length.toString()

};
