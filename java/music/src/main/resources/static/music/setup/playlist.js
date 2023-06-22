const playlistSelect = document.getElementById("playlist-select");

playlistSelect.addEventListener("change", function () {
  const selectedOption = playlistSelect.options[playlistSelect.selectedIndex];
  const selectedPlaylist = selectedOption.value;
  const urlParams = new URLSearchParams(window.location.search);
  urlParams.set("playlist", selectedPlaylist);
  window.location.search = urlParams.toString();
});


$.ajax({
  url: "/playlists/" + playlist,
  dataType: "json",
  success: function (response) {
    // Parse the JSON string and extract the data
    var data = JSON.parse("" + response.data);
    var ids = data[0];
    var links = data[1];
    var images = data[2];
    var titles = data[3];
    var artists = data[4];
    // Do something with the extracted data
    var playlistdata = [];
    playlistdata.push(ids);
    playlistdata.push(links);
    playlistdata.push(images);
    playlistdata.push(titles);
    playlistdata.push(artists);
    console.log(playlistdata)
    //////////////////////////////////////////
    let currentIndex = 0;
    let out = false;

    const audio = document.getElementById("audio");
    const playlistTracks = document.getElementById("playlist-tracks")
    let linksdata = playlistdata[1];
    audio.src = linksdata[currentIndex];
    // 
    for (i = 0; i < playlistdata[0].length; i++) {
      playlistTracks.innerHTML += "<li>" + "<div>" + "<img src='" + playlistdata[2][i] + "'>" + "<div>" + "<span>" + playlistdata[3][i] + "</span>" + "<span>" + playlistdata[4][i] + "</span>" + "</div>" + "</div>" + "</li>"
    }
  },
});