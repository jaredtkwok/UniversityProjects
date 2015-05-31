//*******************************************
//	Code by: Jared Kwok
//*******************************************
using UnityEngine;
using System.Collections;

public class SanityIndicator : MonoBehaviour
{	
		public float maxSanity = 100;
		public static float curSanity = 100;
		public float sanityDecay = 1f;
		public int orbIntake = 0;
		public int nextIntakeVal = 1;
		public float sanityModifier = 0.95f;

		public SanityIndicator ()
		{
		}

		void Start ()
		{

		}

		void Update ()
		{
			if (curSanity < 0.0f) 
			{
					curSanity = 0;
		
			}
			curSanity -= Time.deltaTime * sanityDecay;
			orbIntake = GetPill.orbIntake;
			if(orbIntake == nextIntakeVal){
				maxSanity *= sanityModifier;
				curSanity = maxSanity;
				nextIntakeVal++;
			}

		}


}
