// 데이터
const quizData = [
   
    {
            "id":"1",
            "question":"What does HTML stand for?",
            "answers":
            {
                "A": "Hyper Text Markup Language",
                "B": "Hyperlinks and Text Markup Language",
                "C": "Home Tool Markup Language",
            },
            "correct": "A"
        },
        {
            "id":"2",
            "question":"Who is marking the Web standards?",
            "answers":
            {
                "A": "Microsoft",
                "B": "Google",
                "C": "The World Wide Web Consortium",
                "D": "Mozilla"
            },
            "correct": "C"
        },
        {
            "id":"3",
            "question":"Choose the correct HTML element for the largest heading",
            "answers":
            {
                "A": "‹h6›", 
                "B": "‹heading›",
                "C": "‹h1›",
                "D": "‹head›"
            },
            "correct": "C"
        },
        {
            "id":"4",
            "question":"What is the correct HTML element for inserting a line break?",
            "answers":
            {
                "A": "‹lb›",
                "B": "‹br›",
                "C": "‹break›",
            },
            "correct": "B"
        },
        {
            "id":"5",
            "question":"What is the correct HTML for adding a background color?",
            "answers":
            {
                "A": "‹body bg='yellow'",
                "B": "‹background>yellow</background›",
                "C": "‹body style='background-color:yellow'›",
            },
            "correct": "C"
        },
        {
            "id":"6",
            "question":"Choose the correct HTML element to define important text",
            "answers":
            {
                "A": "‹b›",
                "B": "‹i›",
                "C": "‹important›",
                "D": "‹strong›"
            },
            "correct": "D"
        },
        {
            "id":"7",
            "question":"Choose the correct HTML element to define emphasized text",
            "answers":
            {
                "A": "‹i›",
                "B": "‹italic›",
                "C": "‹em›",
            },
            "correct": "C"
        },
        {
            "id":"8",
            "question":"What is the correct HTML for creating a hyperlink?",
            "answers":
            {
                "A": "‹a›http://www.w3schools.com‹/a›",
                "B": "‹a name='http://www.w3schools.com'›W3Schools‹/a›",
                "C": "‹a href='http://www.w3schools.com'›W3Schools‹/a›",
                "D": "‹a url='http://www.w3schools.com'›W3Schools‹/a›"
            },
            "correct": "C"
        }
    ]
    

 


  //id 선언
  const submitBtn = document.getElementById('submit');
  const prevBtn = document.getElementById('prev');
  const nextBtn = document.getElementById('next');
  
  const quizDisplay = document.getElementById('quiz');
  const resultDisplay = document.getElementById('result');

  

   let currentSlide = 0;
  
  //quizList
  function quizList(){
      const output = [ ]; 
          quizData.forEach((currentQuestion, index) => { 
                         const answers = [];    
                         for(item in currentQuestion.answers){
                                      //대답담기  
                                     answers.push(`<label style="background-color:rgb(245, 117, 138); border-radius: 5px;">
                                                  <input type="radio" name="question${index}" value="${item}">
                                                      ${item} : ${currentQuestion.answers[item]}
                                                  </label>`);
                             }

                              //퀴즈담기
                              output.push(`
                              
                              <div class="slide">
                            <h3>Question ${index+1} of ${quizData.length}</h3>
                                  <div class="question">Q${currentQuestion.id}. ${currentQuestion.question}</div>
                                  <div class="answer"><br>${answers.join('<br><br>')}</div>
                              </div>
                              <div class="slide"><details><summary>정답확인</summary><p>${currentQuestion.correct}</p></details></div>`);
                      }              
         );
           //퀴즈디스플레이영역에 뿌리기
            quizDisplay.innerHTML = output.join('</br>');
         
      }
  
  
      //결과출력results
  function showResult(){
          const answerDisplays = quizDisplay.querySelectorAll('.answer');   //quizDisplay영역에서  <class ="answer">과 일치하는 리스트 반환
          let numCorrect = 0; 

          //forEach (현재처리요소, 인덱스, 배열){}
          quizData.forEach( (currentQuestion, questionNum)=>{  
                  const answerDisplay = answerDisplays[questionNum];  
                  const selector = `input[name=question${questionNum}]:checked`;    //input태그 안에 이름이 question{index} 중 체크된 것/ user의 대답 체크
                  const userAnswer = (answerDisplay.querySelector(selector) || {} ).value;  //user의 대답 반환. 선택했거나 빈 / 빈 상태에서 제출해도 오류가 나지 않도록
  
  
                  if(userAnswer === currentQuestion.correct){    
                          numCorrect++;
                  }else{
                          answerDisplays[questionNum].style.color = 'red';
                  }
          });
        
              const score = `${numCorrect}` * 2.5;  //점수계산


              resultDisplay.innerHTML = `<h3>${numCorrect}/${quizData.length}</h3><hr><h3>${score}점</h3>`; 
             
      
  }

 
  //slide, prev next
  function showSlide(n){
      const slides = document.querySelectorAll('.slide'); 
       slides[currentSlide].classList.remove('active-slide');  //classList.remove - 명시된 클래스를 제거한다.
       slides[n].classList.add('active-slide');  //classList.add - 명시된 클래스를 추가한다.
       currentSlide = n;
         if(currentSlide === 0){
             prevBtn.style.display = 'none';
             submitBtn.style.display = 'none';
             
         }else{
             prevBtn.style.display = 'inline-block';
             nextBtn.style.display = 'inline-block';
            
         }
  
         if(currentSlide === slides.length-1){
             nextBtn.style.display = 'none';
             submitBtn.style.display = 'inline-block';
         } else{ submitBtn.style.display = 'none';
      }  


  }
  

  
  //nextBtn을 누르면 정답 슬라이드가 나와야 한다.
  function showNext(){ showSlide(currentSlide+1);}


  //prevBtn을 누르면 이전 문제가 나와야 한다. - 짝수 슬라이드에서 버튼을 누르면 2칸/ 홀수슬라이드에서 누르면 1칸
  function showPrevSlide(){
      if (currentSlide %2 === 0){
          showSlide(currentSlide-2)}
      else{ 
          showSlide(currentSlide-1)}  
          
      // if(quizData.length < 3){resultDisplay.style.display= 'none';}    

  };

  function showsumbit(){
   showResult();
  };
   
      
   

  //호출
  quizList();


  showSlide(currentSlide);
     

  prevBtn.addEventListener('click',showPrevSlide);
  nextBtn.addEventListener('click',showNext);
  submitBtn.addEventListener('click',showsumbit);