let bubbles = [];
var bubblesCounter;
var xSpeed = 4;
var ySpeed = 4;

function setup() {
  createCanvas(windowWidth, windowHeight);
  for (var i = 0; i < 100; i++) {
    let x = random(0, width - 100);
    let y = random(0, height - 100);
    let r = random(20, 50);
    bubbles[i] = new Bubble(x, y, r);
  }
  bubblesCounter = {
    counter: bubbles.length,
    col: color(192, 192, 192)
  };
}

// Bubbles Remove
function mousePressed() {
  for (var i = 0; i < bubbles.length; i++) {
    bubbles[i].clicked(mouseX, mouseY);
    if (bubbles[i].contains(mouseX, mouseY)) {
      bubbles.splice(i, 1);
    }
  }
}

function draw() {
  background(0);
  // Bubbles "Turn On" when touching each other
  for (b of bubbles) {
    b.show();
    b.move();
    let overlap = false;
    for (other of bubbles) {
      if (b != other && b.intersects(other)) {
        overlap = true;
      }
    }
    if (overlap) {
      b.changeBrightness(255);
    }
    else {
      b.changeBrightness(0);
    }
  }
  bubblesCounter.counter = bubbles.length;
  if (bubblesCounter.counter == 0) {
    alert('You Got All the Bubbles!');
  }
  fill(bubblesCounter.col);
  text(bubblesCounter.counter, 10, 50);
}

class Bubble {
  constructor(x, y, r, xSpeed, ySpeed) {
    this.x = x;
    this.y = y;
    this.r = r;
    this.brightness = 0;
  }

  changeBrightness(value) {
    this.brightness = value;
  }

  clicked(x, y) {
    let d = dist(x, y, this.x, this.y);
    if (d < this.r) {
      this.brightness = 255;
      // console.log("Clicked");
    }
  }

  intersects() {
    let d = dist(this.x, this.y, other.x, other.y);
    return (d < this.r + other.r);
  }

  contains(x, y) {
    let d = dist(x, y, this.x, this.y);
    return (d < this.r);
  }

  move() {
    this.x += xSpeed;
    this.y += ySpeed;
    if (this.x > width - this.r || this.x + this.r <= 0) {
      xSpeed = -xSpeed;
      // console.log("Touched.x");
    }
    if (this.y > height - this.r || this.y + this.r <= 0) {
      ySpeed = -ySpeed;
      // console.log("Touched.y");
    }
  }

  show() {
    stroke(255);
    strokeWeight(5);
    fill(this.brightness, 155);
    ellipse(this.x, this.y, this.r * 2);
  }
}