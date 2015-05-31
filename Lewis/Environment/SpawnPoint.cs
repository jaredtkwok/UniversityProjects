//*******************************************
//	Code by: Jared Kwok
//*******************************************
using UnityEngine;
using System.Collections;

public class SpawnPoint : MonoBehaviour {

	public GameObject Player;

	void Awake() {
		Player = GameObject.FindGameObjectWithTag ("Player");
		SpawnPlayer ();

	}
	

	void SpawnPlayer() {
		Player.transform.position = transform.position;
		this.enabled = false;
	}
}
