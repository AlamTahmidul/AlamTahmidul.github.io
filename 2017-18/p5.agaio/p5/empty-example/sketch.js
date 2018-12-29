var blob;
var blobs = [];
var cenView = 1;

function setup() {
  createCanvas(600, 600);
  blob = new Blob(0, 0, 64);
  for (var i = 0; i < 1000; i++) {
    var x = (random(-width, width));
    var y = (random(-height, height));
    blobs[i] = new Blob(x, y, 16);
  }
}

function draw() {
  background(0);
  // Animation to stay in the center of the screen
  translate(width / 2, height / 2);
  // Drawing the player into the center
  var centerAtt = 64 / blob.r;
  cenView = lerp(cenView, centerAtt, 0.2);
  scale(cenView);
  translate(-blob.position.x, -blob.position.y);

// Drawing the bubbles and "eating" the bubbles
  for (var i = blobs.length - 1; i >= 0; i--) {
    blobs[i].show();
    if (blob.eats(blobs[i])) {
      blobs.splice(i, 1);
    }
  }
  blob.show();
  blob.update();
}
