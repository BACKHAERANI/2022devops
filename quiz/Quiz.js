// 데이터
const quizData = 
   [ {
        "id":"1",
        "question":"What does HTML stand for?",
        "answers":
        {
            "A": "Hyper Text Markup Language",
            "B": "Hyperlinks and Text Markup Language",
            "C": "Home Tool Markup Language"
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
            "C": "‹break›"
        },
        "correct": "B"
    },
    {
        "id":"5",
        "question":"What is the correct HTML for adding a background color?",
        "answers":
        {
            "A": "‹body bg='yellow'›",
            "B": "‹background›yellow‹/background›",
            "C": "‹body style='background-color:yellow'›"
        },
        "correct": "C"
    },
    // {
    //     "id":"6",
    //     "question":"Choose the correct HTML element to define important text",
    //     "answers":
    //     {
    //         "A": "‹b›",
    //         "B": "‹i›",
    //         "C": "‹important›",
    //         "D": "‹strong›"
    //     },
    //     "correct": "D"
    // },
    // {
    //     "id":"7",
    //     "question":"Choose the correct HTML element to define emphasized text",
    //     "answers":
    //     {
    //         "A": "‹i›",
    //         "B": "‹italic›",
    //         "C": "‹em›"
    //     },
    //     "correct": "C"
    // },
    // {
    //     "id":"8",
    //     "question":"What is the correct HTML for creating a hyperlink?",
    //     "answers":
    //     {
    //         "A": "‹a›http://www.w3schools.com‹/a›",
    //         "B": "‹a name='http://www.w3schools.com'›W3Schools‹/a›",
    //         "C": "‹a href='http://www.w3schools.com'›W3Schools‹/a›",
    //         "D": "‹a url='http://www.w3schools.com'›W3Schools‹/a›"
    //     },
    //     "correct": "C"
    // },
    // {
    //     "id":"9",
    //     "question":"Which character is used to indicate an end tag?",
    //     "answers":
    //     {
    //         "A": "/",
    //         "B": "*",
    //         "C": "<",
    //         "D": "^"
    //     },
    //     "correct": "A"
    // },
    // {
    //     "id":"10",
    //     "question":"How can you open a link in a new tab/browser window?",
    //     "answers":
    //     {
    //         "A": "‹a href='url' target='new'›",
    //         "B": "‹a href='url' target='_blank'›",
    //         "C": "‹a href='url' new›"
    //     },
    //     "correct": "B"
    // },
    // {
    //     "id":"11",
    //     "question":"Which of these elements are all ‹table› elements?",
    //     "answers":
    //     {
    //         "A": "‹table›‹head›‹tfoot›",
    //         "B": "‹table›‹tr›‹tt›",
    //         "C": "‹table›‹tr›‹td›",
    //         "D": "‹thead›‹body›‹tr›"
    //     },
    //     "correct": "C"
    // },
    // {
    //     "id":"12",
    //     "question":"Inline elements are normally displayed without starting a new line.",
    //     "answers":
    //     {
    //         "A": "True",
    //         "B": "False"
    //     },
    //     "correct": "A"
    // },
    // {
    //     "id":"13",
    //     "question":"How can you make a numbered list?",
    //     "answers":
    //     {
    //         "A": "‹ul›",
    //         "B": "‹ol›",
    //         "C": "‹dl›",
    //         "D": "‹list›"
    //     },
    //     "correct": "B"
    // },
    // {
    //     "id":"14",
    //     "question":"How can you make a bulleted list?",
    //     "answers":
    //     {
    //         "A": "‹ol›",
    //         "B": "‹dl›",
    //         "C": "‹ul›",
    //         "D": "‹list›"
    //     },
    //     "correct": "C"
    // },
    // {
    //     "id":"15",
    //     "question":"what is the correct HTML for making a checkbox?",
    //     "answers":
    //     {
    //         "A": "‹input type='checkbox'›",
    //         "B": "‹check›",
    //         "C": "‹checkbox›",
    //         "D": "‹input type='check'›"
    //     },
    //     "correct": "A"
    // },
    // {
    //     "id":"16",
    //     "question":"What is the correct HTML for making a text input field?",
    //     "answers":
    //     {
    //         "A": "‹textinput type='text'›",
    //         "B": "‹textfield›",
    //         "C": "‹input type='textfield'›",
    //         "D": "‹input type='text'›"
    //     },
    //     "correct": "D"
    // },
    // {
    //     "id":"17",
    //     "question":"What is the correct HTML for making a drop-down list?",
    //     "answers":
    //     {
    //         "A": "‹list›",
    //         "B": "‹input type='list'›",
    //         "C": "‹select›",
    //         "D": "‹input type='dropdown'›"
    //     },
    //     "correct": "C"
    // },
    // {
    //     "id":"18",
    //     "question":"What is the correct HTML for making a text area?",
    //     "answers":
    //     {
    //         "A": "‹input type='textarea'›",
    //         "B": "‹textarea›",
    //         "C": "‹input type='textbox'›"
    //     },
    //     "correct": "B"
    // },
    // {
    //     "id":"19",
    //     "question":"What is the correct HTML for inserting an image?",
    //     "answers":
    //     {
    //         "A": "‹img alt='MyImage'›image.gif‹/img›",
    //         "B": "‹image src='image.gif' alt='MyImage'›",
    //         "C": "‹img href='image.gif' alt='MyImage'›",
    //         "D": "‹img src='image.gif' alt='MyImage'›"
    //     },
    //     "correct": "D"
    // },
    // {
    //     "id":"20",
    //     "question":"What is the correct HTML for inserting a background image?",
    //     "answers":
    //     {
    //         "A": "‹body style='background-image:url(background.gif)'›",
    //         "B": "‹body bg='background.gif'›",
    //         "C": "‹background img='background.gif'›",
    //     },
    //     "correct": "A"
    // },
    // {
    //     "id":"21",
    //     "question":"An ‹iframe› is used to display a web page within a web page.",
    //     "answers":
    //     {
    //         "A": "False",
    //         "B": "There is no such thing as an ‹iframe›",
    //         "C": "True"
    //     },
    //     "correct": "C"
    // },
    // {
    //     "id":"22",
    //     "question":"HTML comments start with ‹!--and end with--›",
    //     "answers":
    //     {
    //         "A": "True",
    //         "B": "False"
    //     },
    //     "correct": "A"
    // },
    // {
    //     "id":"23",
    //     "question":"Block elements are normally displayed without starting a new line.",
    //     "answers":
    //     {
    //         "A": "False",
    //         "B": "True"
    //     },
    //     "correct": "A"
    // },
    // {
    //     "id":"24",
    //     "question":"Which HTML element defines the title of a document?",
    //     "answers":
    //     {
    //         "A": "‹head›",
    //         "B": "‹title›",
    //         "C": "‹meta›"
    //     },
    //     "correct": "B"
    // },
    // {
    //     "id":"25",
    //     "question":"Which HTML attribute specifies an alternate text for an image, if the image cannot be displayed?",
    //     "answers":
    //     {
    //         "A": "alt",
    //         "B": "longdesc",
    //         "C": "src",
    //         "D": "title"
    //     },
    //     "correct": "A"

    // },
    // {
    //     "id":"26",
    //     "question":"Which doctype is correct for HTML5?",
    //     "answers":
    //     {
    //         "A": "‹!DOCTYPE html›",
    //         "B": "‹!DOCTYPE HTML PUBLIC'-//W3C//DTD HTML 5.0//EN' 'http://www.w3.org/TR/html5/strict.dtd'›",
    //         "C": "‹!DOCTYPE HTML5›"
    //     },
    //     "correct": "A"

    // },
    // {
    //     "id":"27",
    //     "question":"Which HTML element is used to specify a footer for a document or section?",
    //     "answers":
    //     {
    //         "A": "‹section›",
    //         "B": "‹bottom›",
    //         "C": "‹footer›"
    //     },
    //     "correct": "C"
    // },
    // {
    //     "id":"28",
    //     "question":"In HTML you can embed SVG elements directly into an HTML page.",
    //     "answers":
    //     {
    //         "A": "True",
    //         "B": "False"
    //     },
    //     "correct": "A"
    // },
    // {
    //     "id":"29",
    //     "question":"What is the correct HTML element for playing video files?",
    //     "answers":
    //     {
    //         "A": "‹video›",
    //         "B": "‹media›",
    //         "C": "‹movie›"
    //     },
    //     "correct": "A"
    // },
    // {
    //     "id":"30",
    //     "question":"What is the correct HTML element for playing audio files?",
    //     "answers":
    //     {
    //         "A": "‹sound›",
    //         "B": "‹audio›",
    //         "C": "‹mp3›"
    //     },
    //     "correct": "B"
    // },
    // {
    //     "id":"31",
    //     "question":"The HTML global attribute, 'contenteditable'is used to:",
    //     "answers":
    //     {
    //         "A": "Update content from the server",
    //         "B": "Specify whether the content of an element should be editable or not",
    //         "C": "Specifies a context menu for an element. The menu appears when a user right-clicks on the element",
    //         "D": "Return the position of the first found occurrence of content inside a string"
    //     },
    //     "correct": "B"
    // },
    // {
    //     "id":"32",
    //     "question":"In HTML, onblur and onfocus are:",
    //     "answers":
    //     {
    //         "A": "HTML elements",
    //         "B": "Event elements",
    //         "C": "Style elements"
    //     },
    //     "correct": "B"
    // },
    // {
    //     "id":"33",
    //     "question":"Graphics defined by SVG is in which format",
    //     "answers":
    //     {
    //         "A": "CSS",
    //         "B": "XML",
    //         "C": "HTML"
    //     },
    //     "correct": "B"
    // },
    // {
    //     "id":"34",
    //     "question":"The HTML ‹canvas› element is used to:",
    //     "answers":
    //     {
    //         "A": "display database records",
    //         "B": "manipulate data in MySQL",
    //         "C": "create draggable elements",
    //         "D": "draw graphics"
    //     },
    //     "correct": "D"
    // },
    // {
    //     "id":"35",
    //     "question":"In HTML, which attribute is used to specify that an input field must be filled out?",
    //     "answers":
    //     {
    //         "A": "formvaildate",
    //         "B": "validate",
    //         "C": "placeholder",
    //         "D": "required"
    //     },
    //     "correct": "D"
    // },
    // {
    //     "id":"36",
    //     "question":"Which input type defines a slider control?",
    //     "answers":
    //     {
    //         "A": "range",
    //         "B": "search",
    //         "C": "slider",
    //         "D": "controls"
    //     },
    //     "correct": "A"
    // },
    // {
    //     "id":"37",
    //     "question":"Which HTML element is used to display a scalar measurement within a range?",
    //     "answers":
    //     {
    //         "A": "‹measure›",
    //         "B": "‹range›",
    //         "C": "‹gauge›",
    //         "D": "‹meter›"
    //     },
    //     "correct": "D"
    // },
    // {
    //     "id":"38",
    //     "question":"Which HTML element defines navigation links?",
    //     "answers":
    //     {
    //         "A": "‹navigate›",
    //         "B": "‹navigation›",
    //         "C": "‹nav›"
    //     },
    //     "correct": "C"
    // },
    // {
    //     "id":"39",
    //     "question":"In HTML, what does the ‹aside› element define?",
    //     "answers":
    //     {
    //         "A": "Content aside from the page content",
    //         "B": "A navigation list to be shown at the left side of the page",
    //         "C": "The ASCⅡ character-set; to send information between computers on the internet"
    //     },
    //     "correct": "A"
    // },
    // {
    //     "id":"40",
    //     "question":"Which HTML element is used to specify a header for a document or section?",
    //     "answers":
    //     {
    //         "A": "‹head›",
    //         "B": "‹top›",
    //         "C": "‹header›",
    //         "D": "‹section"
    //     },
    //     "correct": "C"
    // }
    
   ]

 

   
    //id 선언
    const submitBtn = document.getElementById('submit');
    const prevBtn = document.getElementById('prev');
    const nextBtn = document.getElementById('next');
    const retryBtn = document.getElementById('retry');
    
    const quizDisplay = document.getElementById('quiz');
    const resultDisplay = document.getElementById('result');

     let currentSlide = 0;
    
    // quizList
    function quizList(){
        const output = [ ]; 
     
            quizData.forEach((currentQuestion, index) => { 
                           const answers = [];    
                           for(item in currentQuestion.answers){
                                        //answers영역 담기  
                                       answers.push(`<label>
                                                    <input type="radio" name="question${index}" value="${item}">
                                                        ${item} : ${currentQuestion.answers[item]}
                                                    </label>`);
                               }

                                //퀴즈슬라이드 생성
                                output.push(`
                                
                                <div class="slide">
                                    <h3 style="padding-left: 20px">Question ${index+1} of ${quizData.length}</h3>
                                    <div class="question">Q${currentQuestion.id}. ${currentQuestion.question}</div>
                                    <div class="answer"><br>${answers.join('<br><br>')}</div><br><br>
                                  <div class="correct" >${currentQuestion.correct}</div>
                                    
                                </div>
                               `);                                    
                        }              
           );
             //퀴즈디스플레이영역에 뿌리기
              quizDisplay.innerHTML = output.join('</br>');
  
        };



    function handlesubmit(){
            const answerDisplays = quizDisplay.querySelectorAll('.answer');   //quizDisplay영역에서  <class ="answer">과 일치하는 리스트 반환
            let numCorrect = 0; 
            const result = [];
            
            //forEach (현재처리요소, 인덱스, 배열){}
            quizData.forEach( (currentQuestion, questionNum)=>{  
                    const answerDisplay = answerDisplays[questionNum];  
                    const selector = `input[name=question${questionNum}]:checked`;    /// user의 대답 체크
                    const userAnswer = (answerDisplay.querySelector(selector) || {}).value;  //user의 대답 반환.

                      if(userAnswer === currentQuestion.correct){    
                            numCorrect++;       
                        }     
      
        });
                const score = Math.round(  `${numCorrect*100/quizData.length}`);  //점수계산
                result.push(`<div><h3>${numCorrect}/${quizData.length}</h3>
                                <h3>${score}점</h3>
                              </div>`)
                             
                submitBtn.style.display = 'none';
                resultDisplay.innerHTML = result.join('</br>'); 
    };

   
    //slide, prev next
    function showSlide(n){
        const slides = document.querySelectorAll('.slide'); 
         slides[currentSlide].classList.remove('active-slide');  //classList.remove - 명시된 클래스를 제거한다. currentSlide를 지우고
         slides[n].classList.add('active-slide');  //classList.add - 명시된 클래스를 추가한다. n(다음 slide)을 추가한다
         currentSlide = n;
           if(currentSlide === 0){
               prevBtn.style.display = 'none';
               submitBtn.style.display = 'none'; 
               nextBtn.style.display = 'inline-block';              
           }else{
               prevBtn.style.display = 'inline-block';
               nextBtn.style.display = 'inline-block';
           }
    
        if(currentSlide === slides.length-1){
            nextBtn.style.display = 'inline-block';
            retryBtn.style.display = 'inline-block';
            submitBtn.style.display = 'inline-block';
            resultDisplay.style.display = 'inline-block';       //퀴즈페이지에서 결과가 보지 않도록 하고 싶다 miss >> 이미 나온 결과는 다시 결과버튼을 누를때까지 남아있음         
        } else{ 
            submitBtn.style.display = 'none';
            resultDisplay.style.display = 'none';
            retryBtn.style.display = 'none';

        }    
        
    };

    
    
    //nextBtn을 누르면 정답 슬라이드가 나와야 한다.
    //정답이 hidden일 때 누르면 visible로 전환
    //1)id를 사용하여 히든 구현 -실패
    //https://jhmocu.tistory.com/104
    //2) div 내 class를 사용하여 히든구현
    //https://www.delftstack.com/ko/howto/javascript/change-css-property-using-javascript/
    //3) querySelector는 배열 내 첫번째 요소를 반환. quertSelectorAll은 문서 내 요소 목록을 반환한다.
    //https://stackoverflow.com/questions/33085889/queryselectorall-style-does-not-work 


    function showcorrect(){ 
        var els = document.querySelectorAll('.correct');
        for (var x = 0; x < els.length; x++)
            els[x].style.display = 'block';};
        
        // document.querySelectoraAll('.correct').style.display= 'block'; 


    function hiddencorrect(){ 
         var els = document.querySelectorAll('.correct');
    for (var x = 0; x < els.length; x++)
        els[x].style.display = 'none'  };
  

    function handleNext(){
        const slides = document.querySelectorAll('.slide'); 

        if(currentSlide === slides.length-1 && nextBtn.value === "next"){
            alert("마지막 페이지 입니다.")}else{
            if(nextBtn.value === "정답확인"){
                nextBtn.value = "next";
                showcorrect();
            }else{
                nextBtn.value = "정답확인";
                hiddencorrect();
                showSlide(currentSlide+1); }
            }
        };
 
    function handlePrevSlide(){ showSlide(currentSlide-1);};
  

    function handleRetry(){
        showSlide(0);
        hiddencorrect();

    };


    //호출
    quizList()
    showSlide(currentSlide);

    prevBtn.addEventListener('click',handlePrevSlide);
    nextBtn.addEventListener('click',handleNext);
    submitBtn.addEventListener('click', handlesubmit); 
    retryBtn.addEventListener('click', handleRetry); 
   