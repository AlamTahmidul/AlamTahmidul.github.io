function Player() {
  this.x = 30;
  this.y = 30;
  this.s = 20;

  this.showPlayer = function() {
    fill(75, 66, 244);
    rectMode(CENTER);
    rect(this.x, this.y, this.s, this.s);
  };

  this.controls = function() {
    // D
    if (keyIsDown(68) && this.x < (width / 2) - 20) {
      this.moveX(10);
    }
    // A
    else if (keyIsDown(65) && this.x > 20 && this.x < width / 2) {
      this.moveX(-10);
    }
    // W
    else if (keyIsDown(87) && this.y > 20) {
      this.moveY(-10);
    }
    // S
    else if (keyIsDown(83) && this.y < height - 20) {
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
//   if (keyIsDown(80)) {
//     var bullet = new Bullet(this.x, this.y);
//     bullets1.push(bullet);
//     if (bullet.passes(0)) {
//       bullets1.splice(0, 1);
//     }
//   }
// }