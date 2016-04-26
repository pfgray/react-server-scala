require("babel-register")({

    // Setting this will remove the currently hooked extensions of .es6, `.es`, `.jsx`
    // and .js so you'll have to add them back if you want them to be used again.
    extensions: [".js"],
    presets: ["react", "es2015"]
});
console.log('hmm');
require('./App.js');
