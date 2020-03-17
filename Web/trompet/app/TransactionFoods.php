<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class TransactionFoods extends Model
{
    protected $tables = 'transaction_foods';
    protected $fillable = ['id_user', 'id_foods', 'quantity', 'total'];

    public function medicines()
    {
        return $this->belongsTo(Medicines::class, 'id_foods');
    }

    public function users()
    {
        return $this->belongsTo(User::class, 'id_user');
    }
}
