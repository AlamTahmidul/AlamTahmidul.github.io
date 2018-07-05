function Playert() {
  this.x = random((width / 2), width - 20);
  this.y = random(20, height);
  this.s = 20;

  this.showPlayer = function() {
    fill(66, 226, 244);
    rectMode(CENTER);
    rect(this.x, this.y, this.s, this.s);
  };

  this.controls = function() {
    // Right Arrow
    if (keyIsDown(39) && this.x > (width / 2) && this.x < width - 20) {
      this.moveX(10);
    }
    // Left Arrow
    else if (keyIsDown(37) && this.x > (width / 2) + 20) {
      this.moveX(-10);
    }
    // Up Arrow
    else if (keyIsDown(38) && this.y > 20) {
      this.moveY(-10);
    }
    // Down Arrow
    else if (keyIsDown(40) && this.y < height - 20) {
      this.moveY(10);
    }
  }

  this.moveX = function(direction) {
    this.x += direction;
  };
  this.moveY = function(direction) {
    this.y += direction;
  };
}

// this.shootPlayer = function() {
//   if (keyIsDown(191)) {
//     var bullet = new Bullet2(this.x, this.y);
//     bullets2.push(bullet);
//     if (bullet.passes(0)) {
//       bullets2.splice(0, 1);
//     }
//   }
// }