var song, amp, fft, slider, playButton;
var forward, backward;
var volumeHist = [];

function setup() {
  audioBackground = createCanvas(windowWidth, windowHeight - 50);
  amp = new p5.Amplitude();
  fft = new p5.FFT(0.55, 1024);
  angleMode(DEGREES);
  // Load Song
  song = loadSound('Pokemon.mp3', load);

  // Jump Forward/Backward
  forward = createButton("Forward (5sec)");
  forward.mousePressed(jumpForward);
  backward = createButton("Backward (5sec)");
  backward.mousePressed(jumpBackward);

  // Slider for Volume Control
  slider = createSlider(0, 1, 0.5, .01);

  // FFT Vars
  w = width / 512;
}

function load() {
  // Button for Play/Pause
  playButton = createButton("Play");
  playButton.mousePressed(togglePlay);
  console.log("Song Loaded");
}

function togglePlay() {
  if (song.isPlaying()) {
    song.pause();
    playButton.html("Play");
  }
  else {
    song.play();
    playButton.html("Pause");
  }
}

function jumpForward() {
  var len = song.currentTime();
  var skipForward = len + 5;
  song.jump(skipForward);
  if (song.duration() == len) {
    loop(0);
    console.log("Finished. Looping ...");
  }
}

function jumpBackward() {
  var len = song.currentTime();
  var skipBackward = len - 5;
  if (len < 5) {
    song.jump(0);
    console.log("Not 5 secs in yet.");
  }
  else {
    song.jump(skipBackward);
    console.log("Went back.");
  }
}

function draw() {
  background(0);
  fill(0);

  // Audio Spectrum
  var audioSpectrum = fft.analyze();
  stroke(255);
  for (var i = 0; i < audioSpectrum.length; i++) {
    var newAmp = audioSpectrum[i];
    var y = map(newAmp, 0, 512, height, 0);
    line(i * w, height, i * w, y);
  }

  // Circular Audio Spectrum
  beginShape();
  //    translate(width / 2, height / 2);
  for (var i = 0; i < audioSpectrum.length; i++) {
    var circleAmp = audioSpectrum[i];
    var r = map(circleAmp, 0, 512, 40, 200);
    var x = r * cos(i);
    var y = r * sin(i);
    vertex(x, y);
  }
  endShape();
  // For Finding the bins (length)
  //  console.log(audioSpectrum.length);

  song.setVolume(slider.value());
  var volume = amp.getLevel();
  volumeHist.push(volume);

  // The Amplitude Graph
  stroke(255);
  noFill();
  var standY = map(volume, 0, 1, height, 0);
  beginShape();
  for (var i = 0; i < volumeHist.length; i++) {
    var y = map(volumeHist[i], 0, 1, height / 4, 0);
    vertex(i, y);
  }
  endShape();

  if (volumeHist.length > width - 25) {
    volumeHist.splice(0, 1);
  }

  // The Circle Animation Boom
  ellipse(width / 2, height / 2, volume * 200, volume * 200);

  // Line on the side animation
  stroke(0, 255, 0);
  translate(0, (height / 2) - standY);
  line(volumeHist.length, height / 2, volumeHist.length, height / 4);
}