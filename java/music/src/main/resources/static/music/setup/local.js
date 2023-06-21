
// Check if a key exists in local storage
if (localStorage.getItem('user') !== null) {
    // The key exists in local storage
    console.log('myKey exists in local storage.');
} else {
    // The key does not exist in local storage
    console.log('myKey does not exist in local storage.');
    window.location.replace('/')
}