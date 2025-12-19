// ----- QUIZ DATA -----
const quizData = [
  { question: 'What is the capital of India?', options: ['Mumbai', 'Delhi', 'Chennai', 'Kolkata'], correct: 'Delhi' },
  { question: 'Which data structure uses LIFO?', options: ['Queue', 'Stack', 'Array', 'Tree'], correct: 'Stack' },
  { question: 'What keyword is used to inherit a class in Java?', options: ['implements', 'inherits', 'extends', 'import'], correct: 'extends' },
  { question: 'Which collection stores unique elements?', options: ['List', 'Set', 'Queue', 'Map'], correct: 'Set' },
  { question: 'What is the default value of a boolean in Java?', options: ['true', 'false', 'null', '0'], correct: 'false' }
];

// ----- STACK (for storing previous questions) -----
let questionStack = [];  // acts as LIFO stack

// ----- HASHMAP (for storing user answers) -----
let answerMap = new Map();  // question -> chosen answer

let currentQuestion = 0;
let score = 0;

const questionEl = document.getElementById('question');
const optionsEl = document.getElementById('options');
const nextBtn = document.getElementById('nextBtn');

function loadQuestion() {
  const q = quizData[currentQuestion];
  questionEl.textContent = q.question;
  optionsEl.innerHTML = '';
  q.options.forEach(opt => {
    const btn = document.createElement('button');
    btn.textContent = opt;
    btn.classList.add('option');
    btn.onclick = () => selectAnswer(opt);
    optionsEl.appendChild(btn);
  });
  nextBtn.classList.add('hidden');
}

function selectAnswer(answer) {
  const currentQ = quizData[currentQuestion];
  const correct = currentQ.correct;

  // Store the question in STACK (push)
  questionStack.push(currentQ.question);

  // Store the answer in HASHMAP (key -> value)
  answerMap.set(currentQ.question, answer);

  // Check correctness
  if (answer === correct) score += 10;

  // Color feedback
  Array.from(optionsEl.children).forEach(btn => {
    btn.disabled = true;
    if (btn.textContent === correct) btn.style.background = 'rgba(0,255,0,0.4)';
    else if (btn.textContent === answer && answer !== correct) btn.style.background = 'rgba(255,0,0,0.4)';
  });

  nextBtn.classList.remove('hidden');
}

nextBtn.onclick = () => {
  currentQuestion++;
  if (currentQuestion < quizData.length) {
    loadQuestion();
  } else {
    showFinalScore();
  }
};

function showFinalScore() {
  const total = quizData.length * 10;
  document.getElementById('finalScore').textContent = `Your Final Score: ${score} / ${total}`;
  document.getElementById('quizContainer').classList.add('hidden');
  document.getElementById('celebration').classList.remove('hidden');

  // Example of using the Stack and Map (in console)
  console.log("🧱 Stack (LIFO order):", questionStack);
  console.log("🧭 HashMap of answers:");
  answerMap.forEach((ans, q) => console.log(`Q: ${q} → A: ${ans}`));
}

function restartQuiz() {
  score = 0;
  currentQuestion = 0;

  // Reset Stack and Map
  questionStack = [];
  answerMap.clear();

  document.getElementById('celebration').classList.add('hidden');
  document.getElementById('quizContainer').classList.remove('hidden');
  nextBtn.classList.add('hidden');

  Array.from(optionsEl.children).forEach(btn => {
    btn.disabled = false;
    btn.style.background = '';
  });

  loadQuestion();
}

document.getElementById('restartBtn').addEventListener('click', restartQuiz);

// Start quiz
loadQuestion();
