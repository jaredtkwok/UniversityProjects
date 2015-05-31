//*******************************************
//	Code by: Lucas
//*******************************************
using UnityEngine;
using System.Collections;

public class GetPill : MonoBehaviour {

	public static int orbIntake = 0;
	public bool ate;
	// Use this for initialization
	void Start () {
		ate = false;
	}

	// Update is called once per frame
	void Update () {
		if (ate) {
			Destroy(this.gameObject);
			orbIntake++;
		}
	}

	void OnTriggerEnter(Collider other) {
		ate = !ate;
	}
}
