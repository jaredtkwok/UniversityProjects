//*******************************************
//	Code by: Jared Kwok
//*******************************************
using UnityEngine;
using System.Collections;

public class CameraMove : MonoBehaviour {

	public GameObject Player;
	public float posX, posY, posZ;
	public float transitionSpeed = 3;
	public float sanityActive = 50;
	public float sanityActive2 = 25;
	public float waitTime = 5;

	public bool camRotated = false;
	public int orbIntake = 0;
	public int nextIntakeVal = 1;
	
	void Awake () {
		Player = GameObject.FindGameObjectWithTag ("Player");
	}
	
	void Update(){
		float curSanity = SanityIndicator.curSanity;
		if (curSanity < sanityActive && curSanity > sanityActive2) {
			shakeCam();	
		} else if (curSanity < sanityActive2) {
			shakeCam();
			transform.rotation = Quaternion.Euler(0,0,180);
			camRotated = true;
		} else {
			moveCam ();
		}

		orbIntake = GetPill.orbIntake;
		if(orbIntake == nextIntakeVal){
			if(camRotated){
				transform.rotation = Quaternion.Euler(0,0,0);
				camRotated = false;
			}
			nextIntakeVal++;
		}
		
		
	}
	
	void moveCam(){
		transform.position = new Vector3 (Player.transform.position.x+posX,
		                                  Player.transform.position.y+posY,
		                                  posZ);
	}

	void shakeCam(){
		transform.position = new Vector3 (Player.transform.position.x +
		                                  (Mathf.PingPong (Time.time * transitionSpeed, posX) - posX / 2),
		                                  Player.transform.position.y + posY,
		                                  posZ);

	}


}
