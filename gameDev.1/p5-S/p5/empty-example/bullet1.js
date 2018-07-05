function Bullet(x, y) {
  this.x = x;
  this.y = y;
  this.r = 8;

  this.show = function() {
    fill(78, 244, 66);
    ellipse(this.x, this.y, this.r * 2, this.r * 2);
  };
  this.passes = function(lol) {
    var dl = dist(this.x, this.y, lol.x, lol.y);
    return (dl < this.r + lol.r);
  }
  this.touches = function(player1) {
    var dis = dist(this.x, this.y, player1.x, player1.y);
    if (dis < (this.r + (player1.s / 2))) {
      return true;
    }
    else {
      return false;
    }
  };

  this.move = function() {
    this.x += 5;
  };
}
