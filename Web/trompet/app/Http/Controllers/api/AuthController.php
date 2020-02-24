<?php

namespace App\Http\Controllers\api;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\User;
class AuthController extends Controller
{

    public function register(Request $request){

        $validateData = $request->validate([
            'name' => 'required|max:55',
            'email' => 'email|required|unique:users',
            'password' => 'required',
            'noHP'=> ' required',
            'address' =>'required'
        ]);
        $validateData['password'] = bcrypt($validateData['password']);

        $user = User::create($validateData);

        $accessToken = $user->createToken('authToken')->accessToken;

        return response(['status'=>'success','user'=>$user,'access_token'=>$accessToken]);

    }
  public function login (Request $request){
      $loginData = $request->validate([
         'email' => 'email|required',
         'password' => 'required'
      ]);
    if(!auth()->attempt($loginData)){
        return response (['status'=>'error','message' => 'invalid credentials']);
    }
    $accessToken = auth()->user()->createToken('authToken')->accessToken;

    return response(['status'=>'success','user'=>auth()->user(),'access_token'=>$accessToken]);
  }
}
