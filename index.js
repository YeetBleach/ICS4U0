const markImg=document.querySelectorAll("img[mark]");
const imgArr = Object.values(markImg);

markImg.forEach((img) => {
	img.addEventListener("click", () => {
		const currentImgOrder = imgArr.indexOf(img);

		if(currentImgOrder == 0){
			const prevCaption0=document.getElementById("currentCaption");
			
			if(prevCaption0 == null){
				const newCaption0 = document.createElement("div");
				newCaption0.classList.add("caption");
				newCaption0.setAttribute("id", "currentCaption");
				
				const answer0 = document.createElement("p");
				answer0.innerHTML = "placeholder";
				
				const oldCaption = document.getElementById("click0");
				oldCaption.appendChild(newCaption0);
				newCaption0.appendChild(answer0);
				}
			else{
				prevCaption0.remove();
			}

		}
		else if (currentImgOrder == 1){
			const prevCaption1 = document.getElementById("currentCaption1");
			if(prevCaption1 == null){
				const newCaption1 = document.createElement("div");
				newCaption1.classList.add("caption");
				newCaption1.setAttribute("id", "currentCaption1");
				
				const answer1 = document.createElement("p");
				answer1.innerHTML = "placeholder1";
				
				const oldCaption1 = document.getElementById("click1");
				oldCaption1.appendChild(newCaption1);
				newCaption1.appendChild(answer1);
			}
			else{
				prevCaption1.remove();
			}
		}

		else if (currentImgOrder == 2){
			const prevCaption2 = document.getElementById("currentCaption2");
			if(prevCaption2 == null){
				const newCaption2 = document.createElement("div");
				newCaption2.classList.add("caption");
				newCaption2.setAttribute("id", "currentCaption2");
				
				const answer2 = document.createElement("p");
				answer2.innerHTML = "placeholder2";
				
				const oldCaption2 = document.getElementById("click2");
				oldCaption2.appendChild(newCaption2);
				newCaption2.appendChild(answer2);
			}
			else{
				prevCaption2.remove();
			}
		}

		else if (currentImgOrder == 3){
			const prevCaption3 = document.getElementById("currentCaption3");
			if(prevCaption3 == null){
				const newCaption3 = document.createElement("div");
				newCaption3.classList.add("caption");
				newCaption3.setAttribute("id", "currentCaption3");
				
				const answer3 = document.createElement("p");
				answer3.innerHTML = "placeholder3";
				
				const oldCaption3 = document.getElementById("click3");
				oldCaption3.appendChild(newCaption3);
				newCaption3.appendChild(answer3);
			}
			else{
				prevCaption3.remove();
			}
		}

	});
});