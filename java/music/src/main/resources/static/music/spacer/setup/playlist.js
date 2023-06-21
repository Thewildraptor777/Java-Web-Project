const playlistSelect = document.getElementById("playlist-select");
      
playlistSelect.addEventListener("change", function() {
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
      var numbers = data[0];
      var mp3Urls = data[1];
      var imageUrls = data[2];
      var strings = data[3];

      // Do something with the extracted data
      var playlistdata = [];
      playlistdata.push(numbers);
      playlistdata.push(mp3Urls);
      playlistdata.push(imageUrls);
      playlistdata.push(strings);
      console.log(playlistdata);
  },
});