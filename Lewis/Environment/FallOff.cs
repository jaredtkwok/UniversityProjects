//*******************************************
//	Code by: Jared Kwok
//*******************************************
using UnityEngine;
using System.Collections;

public class FallOff : MonoBehaviour {
	public GameObject Player;
	public GameObject Spawn;

	void Awake() {
		Player = GameObject.FindGameObjectWithTag ("Player");
		Spawn = GameObject.FindGameObjectWithTag ("Spawn");
	}
	
	void OnTriggerEnter(Collider c){
		if(c.gameObject.tag == "Player"){
			Player.transform.position = Spawn.transform.position;
		}
	
	}

	void Update () {
		transform.Rotate (Vector3.up);
		transform.Rotate (Vector3.down);
	}
}
