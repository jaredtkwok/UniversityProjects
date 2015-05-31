//*******************************************
//	Code by: Jared Kwok
//*******************************************
using UnityEngine;
using System.Collections;

public class CharacterStats : MonoBehaviour {

	public float maxSanity = 100; // Max Sanity
	public float curSanity = 100; // Current Sanity

	public float orbsCollected = 0; // Sanity Orbs Collected
	public float orbModifier = 0.95f; // Decreases as orbs are collected

	public bool death = false;

	public GUIStyle blackBar;
	public GUIStyle sanityBar;
	public GUIStyle sanityBarName;


	public GameObject sanityOrb;

	public GameObject Player;

	public float despawnCountdown = 1;
	public int despawnTimer = 5;

	void Awake () {
		Player = GameObject.FindGameObjectWithTag ("Player");
	}

	void OnGUI(){
		GUI.Box (new Rect(Player.transform.position.x-10,5,200,50),"",blackBar);
		GUI.Box (new Rect(20,5,curSanity,50),"",sanityBar);
	
	}

	void Start () {
	
	}

	void Update () {
	
	}
}
