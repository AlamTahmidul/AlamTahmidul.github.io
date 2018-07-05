// Requires Developer Tools: Console to play
// Rules: First to hit the opponent wins.
// Created by: Tahmidul A. and Darren Tsoi

alert("Requires Developer Tools: Console for rules and controls. Use it to play the game.");
console.log("Welcome to our humble game.");
console.log("Player 1 Move: WASD, Player 1 Shoot: V");
console.log("Player 2 Move: Arrow Keys, Player 2 Shoot: P");
console.log("First one to get hit loses :)");

var player;
var player2;
var bullets1 = [];
var bullets2 = [];
var destroyed1 = 1;
var destroyed2 = 1;

function setup() {
  createCanvas(windowWidth - 5, windowHeight - 5);
  // Making the 2 Players
  player2 = new Playert();
  player = new Player();
}

function draw() {
  background(239, 236, 38);
  // Displaying the 2 players on screen
  player.showPlayer();
  player2.showPlayer();
  // Display
  for (var i = 0; i < bullets1.length; i++) {
    bullets1[i].show();
    bullets1[i].move();
    if (bullets1[i].touches(player2) && destroyed1 != 0) {
      console.log("Works against p2");
      destroyed1 += -1;
      console.log(destroyed1 + "Destroyed!");
    }
  }
  for (var i = 0; i < bullets2.length; i++) {
    bullets2[i].show();
    bullets2[i].move();
    if (bullets2[i].touches(player) && destroyed2 != 0) {
      console.log("Works against p1");
      destroyed2 += -1;
      console.log(destroyed2 + "Destroyed!");
    }
  }
  if (destroyed1 == 0) {
    alert("You won Player 1! Refresh to play again!");
    destroyed1 = 1000;
    console.log("Refresh the page to play again!");
  }
  if (destroyed2 == 0) {
//    bullets2.splice(bullets2.length - 1, 1);
    alert("You won Player 2! Refresh to play again!");
    destroyed2 = 1000;
    console.log("Refresh the page to play again!");
  }
  line(width / 2, 0, width / 2, height);
  player.controls();
  player2.controls();
}

function keyPressed() {
  if (keyCode === 86) {
    var bulleta = new Bullet(player.x, player.y);
    bullets1.push(bulleta);
    if (bulleta.passes(0)) {
      bullets1.splice(0, 1);
    }
  }
  if (keyCode === 80) {
    var bulletb = new Bullet2(player2.x, player2.y);
    bullets2.push(bulletb);
    if (bulletb.passes(0)) {
      bullets2.splice(0, 1);
    }
  }
}