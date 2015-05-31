//*******************************************
//	Code by: Jared Kwok and Nick O'Brien
//*******************************************
using UnityEngine;
using System.Collections;

public class CharacterMovement : MonoBehaviour
{
	public float maxSpeed = 5.0f;
	public bool facingRight = true;
	public float moveDirection;

	Animator anim;

	public float jumpSpeed = 600.0f;
	public float fallSpeed = 100f;
	public bool grounded = false;
	public Transform groundCheck;
	public float groundRadius = 0.2f;
	public LayerMask whatIsGround;


	public float sanityActive = 75;
	public float sanityActive2 = 25;

	void Awake()
	{
		groundCheck = GameObject.Find ("GroundCheck").transform;


	}

	void Start(){

		anim = GetComponent<Animator> ();

	}

	void FixedUpdate () 
	{	float move = Input.GetAxis("Horizontal");
		anim.SetFloat ("Speed",Mathf.Abs(move));

		//grounded = Physics2D.OverlapCircle (groundCheck.position, groundRadius, whatIsGround);
		grounded = Physics.CheckSphere (groundCheck.position,groundRadius, whatIsGround);
		//anim.SetBool ("Ground",grounded);



		rigidbody.velocity = new Vector2 (moveDirection * maxSpeed, rigidbody.velocity.y);

		if (moveDirection > 0.0f && !facingRight)
		{
			Flip();
		}
		else if(moveDirection < 0.0f && facingRight)
		{
			Flip();
		}

	}

	void Update () 
	{	float curSanity = SanityIndicator.curSanity;
		if (curSanity < sanityActive && curSanity > sanityActive2) {
			moveDirection = Input.GetAxis ("IHorizontal");

		} else {
			moveDirection = Input.GetAxis ("Horizontal");
		}
		// Jump key is w
		if (grounded && Input.GetButtonDown ("Jump"))
		{	
			//anim.SetBool("Ground",false);
			rigidbody.AddForce (new Vector2(0, jumpSpeed));

		}
		if(!grounded){
			
			rigidbody.AddForce(new Vector2(0,-fallSpeed));

		}
	}

	// Rotating Character Method
	void Flip()

	{
		facingRight = !facingRight;
		transform.Rotate (Vector3.up, 180.0f,Space.World);

	}
}

