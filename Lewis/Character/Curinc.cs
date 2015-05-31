//*******************************************
//	Code by: Jared Kwok
//*******************************************
using UnityEngine;
using System.Collections;

public class Curinc : MonoBehaviour {
	public float transitionSpeed = 3;
	public GameObject Player;
	public float posX = 6;

	
	void Awake () {
		Player = GameObject.FindGameObjectWithTag ("Player");
		transform.position = new Vector3 (Player.transform.position.x+posX,
		                              Player.transform.position.y+6,
		                              0);
	}
	void Update() {
			transform.position = new Vector3 (Player.transform.position.x +
							(Mathf.PingPong (Time.time * transitionSpeed, posX) - posX / 2),
			                                 	 Player.transform.position.y + 6,0);
		float curSanity = (100 - SanityIndicator.curSanity)/75;
		renderer.material.color = Color.Lerp (Color.white, Color.red, curSanity);
	}
}
