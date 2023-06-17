var pathname = window.location.pathname; 
$(document).ready(function() {
    // Make an AJAX request to the /data endpoint
    $.ajax({
      url: pathname+'/data',
      type: 'GET',
      dataType: 'json',
      success: function(data) {
        // Handle the response data here
        console.log(data);
        playlistArrays=data
        links=playlistArrays[1];
        playlistData=playlistArrays;
    
      },
      error: function(xhr, status, error) {
        // Handle errors here
        console.error(error);
      }
    });
  });