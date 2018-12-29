function Blob(x, y, r) {
  this.position = createVector(x, y);
  this.r = r;
  this.velocity = createVector(0, 0);

  this.update = function() {
    var newVelocity = createVector(mouseX - (width / 2), mouseY - (height / 2));
    newVelocity.setMag(3);
    this.velocity.lerp(newVelocity, 0.1);
    this.position.add(this.velocity);
  }

  this.eats = function(other) {
    var dist = p5.Vector.dist(this.position, other.position);
    if (dist < (this.r + other.r)) {
      var radiusAdd = (PI * this.r * this.r) + (PI * other.r * other.r);
      this.r = sqrt(radiusAdd / PI);
      // this.r += other.r * 0.05;
      return true;
    } else {
      return false;
    }
  }

  this.show = function() {
    fill(255);
    ellipse(this.position.x, this.position.y, this.r * 2, this.r * 2);
  }
}
